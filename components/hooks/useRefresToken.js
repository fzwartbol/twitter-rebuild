import React from 'react'
import axios from './../../api/axios'
import useAuth from './useAuth'

const useRefresToken = () => {
    const { setAuth } = useAuth();

    const refresh = async () => {
        const response = await axios.get('authenticate/refresh', { 
            withCredentials: true
        });
        setAuth(prev => {
            console.log(JSON.stringify(prev));
            console.log(response)
            return {...prev, accessToken: response.data.accessToken}
        })
        return response.data.accessToken;
    }
    

  return refresh;
}

export default useRefresToken