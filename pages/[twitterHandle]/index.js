import Profile from "../../components/profile/Profile";
import {getTwitterHandle} from "../../api/axios";
import Layout from "../../components/layout/Layout";

const index = ({userProfile}) => {
  return (
      <Layout>
        <main className="bg-black min-h-screen flex max-width-[1500px] mx-auto">
          <Profile userProfile={userProfile}/>
        </main>
      </Layout>
  )
}

export default index

export async function getServerSideProps(context) {
  const { twitterHandle} = context.query;
  const userProfile =  await getTwitterHandle(twitterHandle)

  return {
    props: {userProfile: userProfile}, 
  }
}
