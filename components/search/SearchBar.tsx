import React from 'react'

const SearchBar = ({ searchQuery, setSearchQuery, handleSearch} : {
    searchQuery: string, setSearchQuery: Function, handleSearch: Function
}) => {

    const returnSearchQuery = (query: string) => {
        setSearchQuery(query)
    }

    const searchEvent = () => {
        handleSearch() }

    return (
    <div className="pt-4 pb-4 border-gray-600 border-b">
        <div className="bg-[#1d1f23] rounded-full ml-4 mr-4">
            <label htmlFor="header-search">
                <span className="hidden">Search Tweet</span>
            </label>
            <input
                type="text"
                value={searchQuery}
                onInput={e => returnSearchQuery(e.target.value)}
                onKeyPress={event => {
                    if (event.key === 'Enter') {
                        searchEvent()
                    }
                }}
                id="header-search"
                placeholder="Search tweets"
                className="bg-[#1d1f23] rounded-full outline-none p-4  mr-1 "
            />
        </div>
    </div> )
}

export default SearchBar;
