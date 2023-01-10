import React from 'react'
import Search from '../../components/search/Search'
import Layout from "../../components/layout/Layout";
import BlockMid from "../../components/layout/BlockMid";
import BlockRight from "../../components/layout/blockright/BlockRight";

const index = () => {
  
  return (
     <Layout>
         <BlockMid>
            {/*<Search />*/}
         </BlockMid>
         <BlockRight />
    </Layout>
  )}

export default index