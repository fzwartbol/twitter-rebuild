import Sidebar from "./navbar/Sidebar";
import { useRouter } from 'next/router';
import {ReactNode} from "react";


export default function Layout({ children }: { children: ReactNode }) {
  const router = useRouter();

if (router.pathname.includes('/login')) return children;

if (router.pathname.includes('/register')) return children

  return (
      <div className={"flex w-full text-white flex-row"}>
          {/*grow to left */}
          <div className={"grow w-full"} ></div>
          <Sidebar />
              <main className="min-h-screen flex w-full">
                {children}
              </main>
          {/*grow to right*/}
          <div className={"grow w-full"} ></div>
      </div>
  )
}