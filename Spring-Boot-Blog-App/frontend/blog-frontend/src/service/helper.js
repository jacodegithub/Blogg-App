import axios from 'axios'

export const BASE_URL = "http://localhost:8080/blog/api/v1"

export const fetch = axios.create({
    baseURL: BASE_URL,
})

