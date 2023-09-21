import { fetch } from "./helper"

export const getAllPostsService = () => {
    const response = fetch.get(`/posts`)
    const data = response.then(res => res.data)
    return data;
}