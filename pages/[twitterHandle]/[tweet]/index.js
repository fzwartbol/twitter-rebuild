import React from 'react'
import { useRouter } from "next/router";
import TweetDetailPage from "../../../components/tweet/TweetDetailPage";

const index = () => {
  const router = useRouter();

  return (
  <main className="bg-black min-h-screen flex max-width-[1500px] mx-auto">
    <TweetDetailPage />
  </main> 
  )
}

export default index