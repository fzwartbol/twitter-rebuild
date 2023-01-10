import Head from 'next/head';
import Feed from "../components/feed/Feed";
import Layout from "../components/layout/Layout";
import BlockRight from "../components/layout/blockright/BlockRight";
import BlockMid from "../components/layout/BlockMid";
import TrendListComponent from "../components/layout/blockright/trend/TrendListComponent";
import SearchBlock from "../components/layout/blockright/search/SearchBlock";

export default function Home() {
  return (
    <>
      <Head>
        <title>Twitter</title>
        <link rel="icon" href="/favicon.ico" />
      </Head>
        <Layout>
            <BlockMid>
                {/*<Feed />*/}
            </BlockMid>
            <BlockRight>
                <SearchBlock />
                <TrendListComponent />
                <TrendListComponent />
            </BlockRight >
        </Layout>
    </>
  )
}
