import React from 'react'
import { useRef, useState } from 'react';
import { useRouter } from "next/router";
import {CalendarIcon, CameraIcon, ChartBarIcon, FaceSmileIcon, XCircleIcon} from "@heroicons/react/24/outline";

function InputReply({passChildData,tweetId}) {
    const router = useRouter();
    const [input, setInput] = useState("");
    const [selectedFile, setSelectedFile] = useState(null);
    const filePickerRef = useRef(null);

    const addImageToPost = (e) => {
        const reader = new FileReader();
        if (e.target.files[0]) {
            reader.readAsDataURL(e.target.files[0]);
        }
    }
    const sendPost = () => {
        const tweetRequest = {
            message: input,
            userId: 1,
            hashtag: "hashtag"
        }

        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(tweetRequest)
        };

        fetch('http://localhost:8080/tweet/'+router.query.tweet+'/reply', requestOptions)
            .then(async response => {
                const isJson = response.headers.get('content-type')?.includes('application/json');
                const data = isJson && await response.json();
                // check for error response
                if (!response.ok) {
                    // get error message from body or default to response status
                    const error = (data && data.message) || response.status;
                    return Promise.reject(error);
                }
                returnReply(data)
            })
            .catch(error => {
                console.error('There was an error!', error);
            });
        setInput("")
    }

    const returnReply = (data) => {
        passChildData(data);
     }

    return (
        <div className={`border-b border-gray-700 p-3 flex flex-row space-x-3 overflow-y-scroll`}
        >
            <div className="w-full divide-y divide-gray-700 ">
                <div className="flex row m-auto ml-10 mr-10 justify-between">
                    <div className={`${selectedFile && "pb-7"} ${input && "space-y-2.5"}`}>
                        <textarea
                            value={input}
                            onChange={(e) => setInput(e.target.value)}
                            rows="2"
                            placeholder="What would you like to reply?"
                            className="bg-black resize-none outline-none text-[#d9d9d9]
                            text-sm place-holder-gray-500 tracking-wide w-full
                            min-h-[50px]
                            "/>
                    </div>
                    <button className="bg-[#1d9bf0] text-white rounded-full pl-5 pr-5 mt-3 mb-3
font-bold shadow-md hover:bg-[#1a8cd8] disabled:hover:bg-[#1d9bf0] 
disabled:opacity-50 disabled:cursor-default"
                        disabled={!input.trim() && !selectedFile}
                        onClick={sendPost}
                    >Reply</button>
                </div>

                {selectedFile && (
                    <div className="relative">
                        <div className="absolute w-8 h-8 bg-[#15181c] hover:bg-[#272c26] 
            bg-opacity-75 rounded-full flex items-center 
            justify-center top-1 left-1 cursor-pointer"
                            onClick={() => setSelectedFile(null)}
                        >
                            <XCircleIcon className="text-white h-5" />
                        </div>
                        <img src={selectedFile}
                            alt=""
                            className="rounded-2xl max-h-80
            object-contain
            "/>
                    </div>
                )}
                {input && <div className="flex items-center justify-between pt-2.5">
                    <div className="flex items-center">
                        <div className="icon"
                            onClick={() => filePickerRef.current.click()}>
                            <CameraIcon className="h-[22px] text-[#1d9bf0]"
                            />
                            <input type="file"
                                hidden
                                onChange={addImageToPost}
                                ref={filePickerRef} />
                        </div>
                        <div className="icon rotate-90">
                            <ChartBarIcon className="text-[#1d9bf0] h-[22px]" />
                        </div>
                        <div className="icon"
                        // onClick={() => setShowEmojis(!showEmojis)}
                        >
                            <FaceSmileIcon className="text-[#1d9bf0] h-[22px]" />
                        </div>
                        <div className="icon">
                            <CalendarIcon className="text-[#1d9bf0] h-[22px]" />
                        </div>
                    </div>
                </div>
                }
            </div>
        </div>
    )
}

export default InputReply
