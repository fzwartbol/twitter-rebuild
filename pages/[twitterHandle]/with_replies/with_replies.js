import React from 'react'
import { useRouter } from "next/router";
import Profile from "../../components/profile/Profile";
import {getTwitterHandle} from "../../api/axios";
import Layout from "../../../components/layout/Layout";
import BlockMid from "../../../components/layout/BlockMid";


const with_replies = () => {
  const router = useRouter();
  
  return (
    <Layout>
      <BlockMid>
        <Profile userProfile={userProfile}/>
      </BlockMid>
    </Layout>
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