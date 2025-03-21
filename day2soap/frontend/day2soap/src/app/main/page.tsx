'use client'
import React from 'react'
import Main from '../../components/main'
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
