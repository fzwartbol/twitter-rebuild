import React from 'react'
import Image from 'next/image'
import twitterImage from '../../public/lohp_1302x955.png'
import LoginModal from '../../components/login/LoginModal'


const index = () => {
  return (
    <>
      <div className="flex text-white">
        <div className="w-1/2 h-full">
          <Image
            src={twitterImage}
            alt="Picture of the author"
            layout="responsive"
          />
        </div>
        <div className="w-1/2 h-full ">
          <h1 className="text-2xl font-bold p-5">Wat er nu gebeurt</h1>
          <h3 className="text-xl font-bold p-5">Meld je vandaag nog aan bij twitter</h3>
        </div>
      </div>
      <LoginModal />
    </>
  )
}

export default index