'use client'
import Header from '@/components/header'
import React from 'react'
import Main from '../../components/main'
import { AuthProvider } from "@/context/AuthContext";
import { useUser } from '@/context/UserContext'

export default function page() {
  const { user } = useUser();
  console.log(user);
  return (
    <main>
        <Main/>
    </main>
  )
}
