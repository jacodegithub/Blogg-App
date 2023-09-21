
export const doLoggin = (data) => {
    // console.log('localstorage data', data)
    localStorage.setItem('data', JSON.stringify(data))
}

export const isLoggedIn = () => {
    let data = localStorage.getItem('data')
    if(data != null) return true;  
    return false;
}

export const doLogout = () => {
    localStorage.removeItem('data')
}

export const getCurrentUserDetails = () => {
    if(isLoggedIn) {
        return JSON.parse(localStorage.getItem('data')).userDto;
    }
    else {
        return undefined;
    }
}

export const getToken = () => {
    if(isLoggedIn) {
        return JSON.parse(localStorage.getItem('data')).token;
    }
    else return undefined;  
}