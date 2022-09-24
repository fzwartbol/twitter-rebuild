import React from 'react'

const SearchBar = ({ searchQuery, setSearchQuery}) => {
    const returnSearchQuery = (data) => {
        setSearchQuery(data)
    }

    return (
    <div className="pt-4 pb-4 border-gray-600 border-b">
        <div className="bg-gray-600 border-gray-600  focus-within:border-blue-500 rounded-full ml-4 mr-4">
            <label htmlFor="header-search">
                <span className="hidden">Search Tweet</span>
            </label>
            <input
                type="text"
                value={searchQuery}
                onInput={e => returnSearchQuery(e.target.value)}
                id="header-search"
                placeholder="Search tweets"
                className="bg-gray-600 border-none outline-none m-2 p-1"
            />
        </div>
    </div> )
}

export default SearchBar;
