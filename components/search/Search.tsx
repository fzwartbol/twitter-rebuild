import React, {useEffect, useState} from 'react';
import SearchBar from "./SearchBar";
import SearchButton from "./SearchButton"
import {useRouter} from "next/router";
import {getSearchPaginated} from "../../api/axios";
import Spinner from '../spinner/Spinner';
import buttons from "./buttons";
import SearchItems from "./SearchItems";

const Search = () => {
    /** Router query parameters */
    const router = useRouter();

    const [queryData, setQueryData] = useState()
    const [isLoading, setIsLoading] = useState(false)
    const [isError, setIsError] = useState(false)
    const [error, setError] = useState({})
    const [hasNextPage, setHasNextPage] = useState(false)
    const [buttonList, setButtonList] = useState([])

    /** Search query state */
    const [searchQuery, setSearchQuery] = useState("");
    const [source, setSource] = useState("typed_query");
    const [fieldType, setFieldType] = useState("tweet");

    /** Search queryData state */
    const setURLParameters = (query: string, source: string, fieldType: string) => {
        console.log(query, source, fieldType)
        router.push({
            pathname: '/search',
            query: {q: query, src: source, f: fieldType}})
    }

    const fieldTypeButtonClicked = (fieldType: string) => {
        setFieldType(fieldType)
    }

    useEffect(() => {
        if(!router.isReady) return;
        setURLParameters(searchQuery, source, fieldType);

        buttons.map(button => { button.fieldType == fieldType ? button.active = true : button.active = false; return button})
        // setButtonList(buttons.map((button, index) => {
        //     return <SearchButton key={index} name={button.name} fieldType={button.fieldType} active={button.active} link={button.link} buttonClick={fieldTypeButtonClicked}  />
        // }));

        queryRestEndpoint();
    },[router.isReady,fieldType,source,searchQuery])

    /** Parse search Parameters **/
    // useEffect(()=>{
    //     if(!router.isReady) return;
    //     if (paramQuery) setSearchQuery(paramQuery)
    //     if (paramSource) setSource(paramSource)
    //     if (paramFieldType) setFieldType(paramFieldType)
    //     // codes using router.query
    //     // console.log("vuurt af")
    //     // console.log(paramQuery,paramSource,paramFieldType)
    //     // handleSearchQuery(paramQuery,paramSource,paramFieldType)
    // }, [router.isReady]);


    // /** Querying search  */
    // const handleSearchQuery = (paramQuery:string, paramSource:string, paramField:string) => {
    //     // setURLParameters(searchQuery, source, fieldType);
    //     getSearchPaginated(paramQuery, paramField, 0, "id", {})
    //         .then(data => {
    //             setQueryData(data)
    //             console.log(data)
    //             setHasNextPage(Boolean(!data.last))
    //             setIsLoading(false)
    //         })
    //         .catch(() => {
    //             setIsLoading(false)
    //             // if (signal.aborted) return
    //             setIsError(true)
    //             setError({message: error.message})
    //         })
    // }

    const handleSearchQuery = () => {
        // queryRestEndpoint()
    }

    const queryRestEndpoint = () => {
        getSearchPaginated(searchQuery, fieldType, 0, "id", {})
            .then(data => {
                setQueryData(data)
                console.log(data)
                setHasNextPage(Boolean(!data.last))
                setIsLoading(false)
            })
            .catch(() => {
                setIsLoading(false)
                // if (signal.aborted) return
                setIsError(true)
                // setError({message: error.message})
            })
    }



    return (
        <div className={"w-full"}>
            <SearchBar searchQuery={searchQuery} setSearchQuery={setSearchQuery} handleSearch={handleSearchQuery}/>
            {<div className="flex w-full justify-between border-b border-gray-700"> {buttonList}</div>}
            {isLoading && <Spinner/>}
            {queryData && <SearchItems queryData={queryData}/> }
        </div>
    )
}
export default Search;
