import React from 'react'
import Layout from "../../components/layout/Layout";
import BlockMid from "../../components/layout/BlockMid";
import BlockRight from "../../components/layout/blockright/BlockRight";
import Explore from "../../components/explore/Explore";

const index = () => {
  return (
     <Layout>
         <BlockMid>
            <Explore />
         </BlockMid>
         <BlockRight />
    </Layout>
  )}

export default index