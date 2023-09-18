import axios from 'axios'
import { getToken } from './sessionService'

export const BASE_URL = "http://localhost:8080/blog/api/v1"

// FETCH REST API URL
export const fetch = axios.create({
    baseURL: BASE_URL,
})

// FETCH WITH JWT TOKEN PRIVATE REST API URL 
export const privateFetch = axios.create({
    baseURL: BASE_URL
})

privateFetch.interceptors.request.use((config) => {
        const token = getToken();

        if(token) {
            config.headers['Authorization'] = `Bearer ${token}`
        }

        return config;
    },
    (error) => { return Promise.reject(error) }
)

