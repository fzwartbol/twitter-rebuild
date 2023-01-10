import {ReactNode} from "react";

export default function BlockMid({ children }: { children: ReactNode }) {

  return (
    <>
        <div className={"text-white flex w-[600px] border-l border-r border-gray-700 "}>
            {children}
      </div>
    </>
  )
}