import React from 'react'
import Layout from "../components/layout/Layout"

import RegistrationForm from "../../components/registration/RegistrationForm";

const index = () => {
  return (
      <Layout>
        <div className="text-white flex-grow border-l border-r border-gray-700 max-w-2xl sm:ml-[73px] xl:ml-[370px]">
            <RegistrationForm />
        </div>
      </Layout>
  )
}

export default index