import React from 'react'
import TweetDetailPage from "../../../components/tweet/TweetDetailPage";
import Layout from "../../../components/layout/Layout";
import BlockMid from "../../../components/layout/BlockMid";
import BlockRight from "../../../components/layout/blockright/BlockRight";

const index = () => {

  return (
  <Layout>
      <BlockMid>
        <TweetDetailPage />
      </BlockMid>
      <BlockRight>
      </BlockRight>
  </Layout>
  )
}

export default index