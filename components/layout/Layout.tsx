import Sidebar from "./navbar/Sidebar";
import { useRouter } from 'next/router';

interface Props {
    children: React.ReactNode
}

export default function Layout({ children }: Props) {
  const router = useRouter();

if (router.pathname.includes('/login')) return children;

if (router.pathname.includes('/register')) return children

  return (
    <>
      <Sidebar />
      <main>{children}</main>
    </>
  )
}