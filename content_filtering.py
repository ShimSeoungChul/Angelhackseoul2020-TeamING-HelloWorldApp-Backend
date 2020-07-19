import pandas as pd
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.metrics.pairwise import cosine_similarity

df_total = pd.read_csv("content.csv", low_memory=False)

id_list = df_total["id"].tolist()
name_list = df_total["name"].tolist()
category1_list = df_total["category1"].tolist()
category2_list = df_total["category2"].tolist()
goal_list = df_total["goal"].tolist()
status_list = df_total["status"].tolist()
price_list = df_total["price"].tolist()

total_list = []
for i in range(0, len(id_list)):
    total_list.append(name_list[i] + " " + category1_list[i] + " " + category2_list[i] + " " + str(goal_list[i]) + " " +status_list[i] + " " +str(price_list[i]))

count_vector = CountVectorizer(ngram_range=(1, 3))
c_vector_genres = count_vector.fit_transform(total_list)

gerne_c_sim = cosine_similarity(c_vector_genres, c_vector_genres).argsort()[:, ::-1]


def get_recommend_movie_list(df, id, top=6):
    target_movie_index = df[df['id'] == id].index.values
    sim_index = gerne_c_sim[target_movie_index, :top].reshape(-1)
    sim_index = sim_index[sim_index != target_movie_index]

    result = df.iloc[sim_index][:10]
    return result["id"].tolist()


recommend_list = []
for i in range(0, len(id_list)):
    temp_list = get_recommend_movie_list(df_total, id=id_list[i])
    temp_list = str(temp_list)
    temp_list = temp_list[1:-1]
    recommend_list.append(temp_list)


df_recommend = pd.DataFrame(recommend_list)
total = pd.concat([df_total, df_recommend], axis=1)
total.columns = ['id', 'name', 'category1', 'category2', 'goal', 'status', 'price', "recommend"]
total.to_csv("ranking_content.csv", index=False)