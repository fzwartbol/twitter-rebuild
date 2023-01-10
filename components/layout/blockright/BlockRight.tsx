import {ReactNode} from "react";

export default function BlockRight({ children }: { children: ReactNode }) {

  return (
    <>
        <div className={"text-white flex-col ml-6 mt-4 w-[348px] hidden lg:flex"}>
            {children}
      </div>
    </>
  )
}