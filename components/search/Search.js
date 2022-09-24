import React, {useEffect, useState} from 'react'
import SearchBar from "./SearchBar";
import SearchSelectionButton from "./SearchSelectionButton"
import Trending from "./trending/Trending";
import TopLiked from './trending/TopLiked';
import {getSearchTweetsPaginated} from "../../api/axios";
import Tweet from "../tweet/Tweet";
import Spinner from '../spinner/Spinner';

const buttons = [
    {name: 'Top', active: true, link:""},
    {name: 'Newest', active: false, link:"/with_replies"},
    {name: 'Persons', active: false, link:""},
    {name: 'Photos', active: false, link:""},
    {name: 'Videos', active: false, link:""}
]


const Search = () => {
    const [selected, setSelected] = useState("");
    const [searchQuery, setSearchQuery] = useState("");
    const [results, setResults] = useState([])
    const [isLoading, setIsLoading] = useState(false)
    const [isError, setIsError] = useState(false)
    const [error, setError] = useState({})
    const [hasNextPage, setHasNextPage] = useState(false)
    const [buttonList, setButtonList] = useState(buttons);

    useEffect(() => {
        setButtonList(buttons);
    },[])
   
    useEffect(() => {
        setIsLoading(true)
        setIsError(false)
        setError({})
        const controller = new AbortController()
        const {signal} = controller

        getSearchTweetsPaginated(searchQuery, 0, "id", {signal})
            .then(data => {
                setResults(data.content)
                console.log(results)
                setHasNextPage(Boolean(!data.last))
                setIsLoading(false)
            })
            .catch(() => {
                setIsLoading(false)
                if (signal.aborted) return
                setIsError(true)
                setError({message: error.message})
            })
    },[searchQuery])

    const feedButtons =  buttonList.map(button => {
        return <SearchSelectionButton button= {button} setSelected={setSelected}/>
    })

    return (
    <>
        <SearchBar
            searchQuery={searchQuery}
            setSearchQuery={setSearchQuery}
        />
        {/* <div className="flex w-full justify-between">
                {buttonList.map(button =>  <SearchSelectionButton button={button} setSelected={setSelected}/> )}
        </div> */}
        {<div className="flex w-full justify-between"> {feedButtons}</div> }
        {isLoading && <Spinner />}
        {/* <Spinner /> */}
        {searchQuery == "" && <Trending searchQuery={searchQuery} setSearchQuery={setSearchQuery}/>}
        {searchQuery == "" && <TopLiked />}
        {results && results
            .map((tweet, i) => {
                if (results.length === i+1) return <Tweet  key={tweet.id} tweet={tweet}/>
                return <Tweet  key={tweet.id} tweet={tweet} />
            })}
    </>
    )}
export default Search;