import Image from "next/image";
import Header from "../components/header"
import Login from "@/components/login";

export default function Home() {
  return (
    <main>
      <Header/>
      <Login/>
    </main>
    
  );
}
