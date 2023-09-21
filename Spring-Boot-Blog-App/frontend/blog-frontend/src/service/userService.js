import { privateFetch } from "./helper"

export const getUserPosts = (userId) => {
    console.log('passing id', userId)
    const response = privateFetch.get(`/posts/user/${userId}`);
    const data = response.then(res => res.data);
    return data;
}