import React from 'react'
import { useRouter } from "next/router";
import Profile from "../../components/profile/Profile";
import {getTwitterHandle} from "../../api/axios";


const with_replies = () => {
  const router = useRouter();
  
  return (
    <main className="bg-black min-h-screen flex max-width-[1500px] mx-auto">
    <Profile userProfile={userProfile}/>
  </main>
  )
}

export default with_replies


export async function getServerSideProps(context) {
  console.log(context.query)
  const { twitterHandle} = context.query;
  const userProfile =  await getTwitterHandle(twitterHandle)

  return {
    props: {userProfile: userProfile}, 
  }
}