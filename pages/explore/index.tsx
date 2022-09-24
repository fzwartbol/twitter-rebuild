import React from 'react'
import Search from '../../components/search/Search'
import Layout from "../../components/layout/Layout";

const index = () => {
  
  return (
     <Layout>
        <div className="text-white flex-grow border-l border-r border-gray-700 max-w-2xl sm:ml-[73px] xl:ml-[370px]">
          <Search />
        </div>
    </Layout>
  )}

export default index