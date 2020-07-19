import pandas as pd
from sklearn.metrics.pairwise import cosine_similarity


def load_data():
    df_content = pd.read_csv("content.csv")
    df_content.columns = ['product_id', 'name', 'category1', 'category2', 'goal', 'status', 'price']

    df_collaborative = pd.read_csv("event.csv")
    userId_list = df_collaborative["user_id"].tolist()
    productId_list = df_collaborative["product_id"].tolist()

    user_id_unique = list(set(userId_list))
    product_id_unique = list(set(productId_list))

    return df_content, df_collaborative, user_id_unique, product_id_unique


def main():
    df_content, df_collaborative, user_id_unique, product_id_unique = load_data()

    new_user_id_list, new_product_id_list, new_rating_list = [], [], []

    for user_id in user_id_unique:
        for product_id in product_id_unique:
            total = df_collaborative[(df_collaborative.user_id == user_id)]
            total = total[(df_collaborative.product_id == product_id)]

            new_user_id_list.append(user_id)
            new_product_id_list.append(product_id)
            new_rating_list.append(sum(total["weight"]))


    df_userId = pd.DataFrame(new_user_id_list)
    df_productId = pd.DataFrame(new_product_id_list)
    df_rating = pd.DataFrame(new_rating_list)

    df_rating_total = pd.concat([df_userId, df_productId, df_rating], axis=1)
    df_rating_total.columns = ['user_id', 'product_id', 'rating']

    user_movie_rating = pd.merge(df_rating_total, df_content, on='product_id')

    movie_user_rating = user_movie_rating.pivot_table('rating', index='product_id', columns='user_id')
    movie_user_rating.fillna(0, inplace=True)

    item_based_collabor = cosine_similarity(movie_user_rating)
    item_based_collabor = pd.DataFrame(data=item_based_collabor, index=movie_user_rating.index, columns=movie_user_rating.index)

    id_list = user_movie_rating["product_id"].tolist()
    recommend_list = []
    for i in range(0, len(id_list)):
        temp_list = item_based_collabor[id_list[i]].sort_values(ascending=False)[:6]
        temp_list = temp_list.index.tolist()
        temp_list = str(temp_list)
        temp_list = temp_list[1:-1]
        recommend_list.append(temp_list)

    df_recommend = pd.DataFrame(recommend_list)

    total = pd.concat([user_movie_rating, df_recommend], axis=1)
    total.columns = ['user_id', 'product_id', 'rating', 'name', 'category1', 'category2', 'goal', 'status', 'price', "recommend"]
    total.to_csv("ranking_collaborative.csv", index=False)


if __name__ == '__main__':
    main()