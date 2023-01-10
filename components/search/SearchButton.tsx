import React from 'react'

const SearchButton = ({
                          name,
                          active,
                          fieldType,
                          buttonClick,
                      }: { name: string, active: string, fieldType: string, buttonClick: Function }) => {

    const handleButton = () => { buttonClick(fieldType)}

    return (
        <button
            className={` p-2 m-auto hover:bg-gray-700 hover:text-white  ${active ? 'border-b-2 font-bold text-white border-blue-600' : 'text-gray-500'} `}
            onClick={e => handleButton()}
        >{name}</button>
    )
}

export default SearchButton
