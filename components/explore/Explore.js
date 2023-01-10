import React, {useEffect, useState} from 'react'
import SearchBar from "../search/SearchBar";
import Trending from "./trending/Trending";
import TopLiked from './trending/TopLiked';
import {getSearchPaginated} from "../../api/axios";
import Spinner from '../spinner/Spinner';
import {useRouter} from "next/router";


const Explore = () => {
    const router = useRouter();
    const { name, location } = router.query;
    const [searchQuery, setSearchQuery] = useState("");
    const [results, setResults] = useState([])
    const [isLoading, setIsLoading] = useState(false)
    const [isError, setIsError] = useState(false)
    const [error, setError] = useState({})
    const [hasNextPage, setHasNextPage] = useState(false)

    // useEffect(() => {
    //     setIsLoading(true)
    //     setIsError(false)
    //     setError({})
    //     const controller = new AbortController()
    //     const {signal} = controller
    //
    //     getSearchPaginated(searchQuery, selected.type, 0, "id", {signal})
    //         .then(data => {
    //             setResults(data.content)
    //             console.log(data)
    //             console.log(results)
    //             setHasNextPage(Boolean(!data.last))
    //             setIsLoading(false)
    //         })
    //         .catch(() => {
    //             setIsLoading(false)
    //             if (signal.aborted) return
    //             setIsError(true)
    //             setError({message: error.message})
    //         })
    // },[searchQuery])

    return (
    <div className={"w-full"}>
        <SearchBar searchQuery={searchQuery} setSearchQuery={setSearchQuery} />
        {isLoading && <Spinner />}
        {<Trending searchQuery={searchQuery} setSearchQuery={setSearchQuery}/>}
        {<TopLiked />}
    </div>
    )}
export default Explore;