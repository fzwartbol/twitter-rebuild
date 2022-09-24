import Head from 'next/head';
import Feed from "../components/feed/Feed";
import {useRouter} from "next/router";
import {useState} from "react";
import Layout from "../components/layout/Layout";

export default function Home() {
  return (
    <>
      <Head>
        <title>Twitter</title>
        <link rel="icon" href="/favicon.ico" />
      </Head>
        <Layout>
          <main className="bg-black min-h-screen flex max-width-[1500px] mx-auto">
            <Feed />
          </main>
        </Layout>
    </>
  )
}
