import Profile from "../../components/profile/Profile";
import {getTwitterHandle} from "../../api/axios";
import Layout from "../../components/layout/Layout";
import BlockMid from "../../components/layout/BlockMid";
import BlockRight from "../../components/layout/blockright/BlockRight";

const index = ({userProfile}) => {
  return (
      <Layout>
          <BlockMid>
            <Profile userProfile={userProfile}/>
          </BlockMid>
          <BlockRight>
          </BlockRight>
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
