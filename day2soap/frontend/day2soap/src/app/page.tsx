import Image from "next/image";
import Header from "../components/header"
import Login from "@/components/login";
import { AuthProvider } from "@/context/AuthContext";

export default function Home() {
  return (
      <Login/>
  );
}
