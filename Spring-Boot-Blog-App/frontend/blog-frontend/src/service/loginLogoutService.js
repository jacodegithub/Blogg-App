import { fetch } from "./helper"

export const SignUp = (user) => {
    const response = fetch.post('/register', user)
    const data = response.then(res => res.data).catch(error => {
        console.log('axios reviever error ->', error)
    })
    return data;
}

export const LoginService = (credentials) => {
    const response = fetch.post('/login', credentials)
    const data = response.then(res => res.data)
    return data;
}

