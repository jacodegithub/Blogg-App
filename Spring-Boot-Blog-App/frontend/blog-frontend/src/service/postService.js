import { fetch, privateFetch } from "./helper"

export const getAllPostsService = () => {
    const response = fetch.get(`/posts`)
    const data = response.then(res => res.data)
    return data;
}

//http://localhost:8080/blog/api/v1/post/2

export const getAllPostsByIdService = (postId) => {
    const response = privateFetch.get(`/post/${postId}`)
    const data = response.then(res => res.data)
    return data;
}