import React, {useState, useEffect} from 'react'
import { useRouter } from "next/router";

const ButtonFeed = ({button, setSelected}) => {

const handleButton = () => { 
    setSelected(button)
}

  return (
    <button className={` p-2 m-auto hover:bg-gray-700 hover:text-white  ${button.active ? 'border-b-2 font-bold text-white border-blue-600':'text-gray-500'} `}
          onClick={e => handleButton()}
    >{button.name}</button>
  )
  
}

export default ButtonFeed
