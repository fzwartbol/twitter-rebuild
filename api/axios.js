import axios from 'axios';


export default axios.create({
    baseURL: process.env.NEXT_PUBLIC_BACKEND_BASEPATH_URL

})

export const axiosPrivate = axios.create({
    baseURL: process.env.NEXT_PUBLIC_BACKEND_BASEPATH_URL,
    headers: {'Content-Type': 'application/json'},
    withCredentials: true
})


export const API_BASE_URL = axios.create({
    baseURL: process.env.NEXT_PUBLIC_BACKEND_BASEPATH_URL

})

/** Feed Paginated */
export const getFeedPaginated = async (userId= 1, pageParam = 1, options = {}) => {
    const response = await API_BASE_URL.get(`/user/${userId}/feed?page=${pageParam}`,options)
    return response.data
}


/** Feed Paginated */
export const getTweetsPaginated = async (pageParam = 1, options = {}) => {
    const response = await API_BASE_URL.get(`/tweet/paginated?page=${pageParam}`,options)
    return response.data
}

/** Feed Paginated */
export const getTweetsUserPaginated = async (userId, pageParam = 0, options = {}) => {
    const response = await API_BASE_URL.get(`/tweet/user/${userId}/?page=${pageParam}`,options)
    return response.data
}

export const getTweetsAndRepliesUserPaginated = async (userId, pageParam = 0, options = {}) => {
    const response = await API_BASE_URL.get(`/tweet/user/${userId}/with_replies`,options)
    return response.data
}

export const getTweetsUserWithMedia = async (userId, pageParam = 0, options = {}) => {
    const response = await API_BASE_URL.get(`/tweet/user/${userId}/media`,options)
    return response.data
}

export const getTweetsLikedByUserPaginated = async (userId, pageParam = 0, options = {}) => {
    const response = await API_BASE_URL.get(`/tweet/liked/user/${userId}`,options)
    return response.data
}


export const getTrendingHashtags = async ( options = {}) => {
    const response = await API_BASE_URL.get(`/trending/hot`,options)
    return response.data
}

export const getTopReplied = async ( options = {}) => {
    const response = await API_BASE_URL.get(`/trending/replied`,options)
    return response.data
}

export const getTopLiked = async ( options = {}) => {
    const response = await API_BASE_URL.get(`/trending/liked`,options)
    return response.data
}

/** Search Paginated */
export const getSearchTweetsPaginated = async (hashtags = {},pageParam = 1, sortParam = {}, options = {}) => {
    const params = new URLSearchParams();
    params.append(`hashtag`,hashtags)
    params.append(`page`,pageParam)
    params.append(`sort`,sortParam)
    const response = await API_BASE_URL.get(`/tweet/paginated/search?hashtag=${encodeURIComponent(hashtags)}&page=${pageParam}&sortBy=${sortParam}`,options)
    // const response = await API_BASE_URL.get(`/tweet/paginated/search`,params)
    return response.data
}

/** CRUD operations tweets */

export const postTweet = async (options = {}) => {
    const response = await API_BASE_URL.post(`/tweet`,options)
    return response.data
}

export const getTweet = async (tweetId = {}, options = {}) => {
    const response = await API_BASE_URL.get(`/tweet/${tweetId}`,options)
    return response.data
}

/**  Profiles */

export const getTwitterHandle = async (twitterHandle = "test", options = {}) => {
    const response = await API_BASE_URL.get(`/user/handle/${twitterHandle}`,options)
    return response.data
}


/** unFolllow/Follow User */

export const postFollowUser = async (followee = {}, follower = {}, options = {}) => {
    const response = await API_BASE_URL.post(`/user/${followee}/follow/${follower}`,options)
    return response.data
}

export const postUnfollowUser = async (followee = {}, follower = {}, options = {}) => {
    const response = await API_BASE_URL.post(`/user/${followee}/unfollow/${follower}`,options)
    return response.data
}