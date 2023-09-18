import { privateFetch } from "./helper"

export const getAllPostsService = () => {
    const response = privateFetch.get(`/posts`)
    const data = response.then(res => res.data)
    return data;
}