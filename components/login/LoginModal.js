import React, { useState, useRef, useEffect, useContext } from "react";
import AuthContext from "../../context/AuthProvider"
import Image from "next/image";
import { API_BASE_URL } from '../../api/axios'
import useRefresToken from '../hooks/useRefresToken'
import { useRouter } from "next/router";
import useAuth from '../hooks/useAuth'


const LoginModal = () => {
  const router = useRouter()
  const { setAuth, auth } = useAuth();
  const userNameRef = useRef();
  const errRef = useRef();

  const [userName, setUserName] = useState("");
  const [password, setPassword] = useState("");
  const [errMsg, setErrMsg] = useState('');
  const [success, setSuccess] = useState('');

  const refresh = useRefresToken();


  useEffect(() => {
    console.log(errMsg)
  }, [errMsg])

  useEffect(() => {
    setErrMsg('');
  }, [userName, password])

  const handleInputChange = (e) => {
    const { id, value } = e.target;
    if (id === "email") { setUserName(value); }
    if (id === "password") { setPassword(value); }
  }

  const [showModal, setShowModal] = React.useState(true);

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const payload = new URLSearchParams();
      payload.append('username',userName)
      payload.append('password',password)
      const response =  await API_BASE_URL.post(`/authenticate`,payload, {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        }
      });

      const accessToken = response?.data?.accessToken;
      const roles = response?.data?.roles;

      console.log(response?.data);
      setAuth({userName, password, accessToken});

      console.log(auth)
      
      setUserName('');
      setPassword('');
      setSuccess(true)
      setShowModal(false)
      router.push("/");

    } catch (err) {
      if(!err?.response) {
        setErrMsg('No Server Response');
      } else if (err.response?.status === 400) {
        setErrMsg('Missing Username or Password');
      } else if (err.response?.status === 401) {
        setErrMsg('Unauthorized')
      } else {
        setErrMsg('Invalid')
      }

    }
  }


  return (
      <>
        {showModal ? (
            <>
              <div
                  className="justify-center items-center flex overflow-x-hidden overflow-y-auto fixed inset-0 z-50 outline-none focus:outline-none bg-gray-600 bg-opacity-50"
              >
                <div className="relative w-96 my-6 mx-auto max-w-2xl">
                  {/*content*/}
                  <div className="border-0 rounded-lg shadow-lg relative p-5  ml-5 mr-5  justify-start flex flex-col w-full bg-black outline-none focus:outline-none">
                    <div className="flex items-center justify-around">
                      <button
                          className=" text-white block absolute align-left justify-around m-auto background-transparentfont-bold uppercase px-6 py-2 text-sm outline-none focus:outline-none mr-1 mb-1 ease-linear transition-all duration-150"
                          type="button"
                          onClick={() => setShowModal(false)}
                      >
                        x
                      </button>
                      <div className="flex items-center justify-center w-14 h-14
                                                    hoverAnimation p-0 xl:ml-24">
                        <Image src="https://rb.gy/ogau5a" width={30} height={30} />
                      </div>
                    </div>
                    {/*header*/}
                    <div className="flex items-start justify-between p-5 border-b border-solid border-slate-200 rounded-t">
                      <h3 className="text-3xl font-semibold  text-white text-center">
                        Login to Twitter
                      </h3>
                      <button
                          className="p-1 ml-auto bg-transparent border-0 text-black opacity-5 float-right text-3xl leading-none font-semibold outline-none focus:outline-none"
                          onClick={() => setShowModal(false)}
                      >
                      </button>
                    </div>

                    <form onSubmit={handleSubmit} className="relative p-6 flex-auto">
                      <div className="email p-5">
                        <input type="email" id="email" className="form__input p-5 w-full border border-gray-500 bg-black text-white outline-none focus:border-[#1d9bf0]"
                               value={userName}
                               onChange={(e) => handleInputChange(e)}
                               placeholder="Email"
                               autocomplete="off"
                               required />
                      </div>
                      <div className="password p-5">
                        <input className="form__input p-5 w-full border border-gray-500 bg-black text-white outline-none focus:border-[#1d9bf0]" type="password" id="password"
                               value={password}
                               onChange={(e) => handleInputChange(e)}
                               placeholder="Password"
                               autocomplete="off"
                               required />
                      </div>
                      <div className="flex items-center m-auto p-6 border-t border-solid rounded-b">
                        <button
                            className="bg-white text-black active:bg-emerald-600 font-bold uppercase text-sm px-6 py-3 rounded shadow hover:shadow-lg outline-none focus:outline-none mr-1 mb-1 ease-linear transition-all duration-150"
                            type="submit"
                        >
                          Login
                        </button>
                        <button className="bg-white text-black active:bg-emerald-600 font-bold uppercase text-sm px-6 py-3 rounded shadow hover:shadow-lg outline-none focus:outline-none mr-1 mb-1 ease-linear transition-all duration-150" onClick={() => refresh()}>Refresh</button>
                      </div>
                      
                    </form>
                    {/*footer*/}
                    
                  </div>
                </div>
              </div>
              <div className="opacity-25 fixed inset-0 z-40 bg-black"></div>
              
            </>
        ) : null}
      </>
      
  );
}

export default LoginModal