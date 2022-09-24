import {useState, useEffect} from 'react';
import { getFeedPaginated} from '../../api/axios'

const useTweets = (pageNum = 0) => {
    const [results, setResults] = useState([])
    const [isLoading, setIsLoading] = useState(false)
    const [isError, setIsError] = useState(false)
    const [error, setError] = useState({})
    const [hasNextPage, setHasNextPage] = useState(false)

    useEffect(() => {
        setIsLoading(true)
        setIsError(false)
        setError({})

        const controller = new AbortController()
        const { signal } = controller

        getFeedPaginated(1, pageNum, { signal })
            .then(data => {
                setResults(prev => [...prev, ...data.content])
                setHasNextPage(Boolean(!data.last))
                setIsLoading(false)
            })
            .catch(() => {
                setIsLoading(false)
                if(signal.aborted) return
                setIsError(true)
                setError({message: e.message})
            })
        
        return () => controller.abort()

    }, [pageNum])

    return { isLoading, isError, error, results, hasNextPage}
}
export default useTweets