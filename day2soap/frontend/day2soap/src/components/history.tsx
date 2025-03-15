"use client"
import { useUser } from '@/context/UserContext'

export default function History() {
  const { user } = useUser();
  console.log(user);
  return (
    <main className='flex justify-center align-middle items-center m-[8rem]'>
        <div className='bg-white p-6 rounded-lg shadow-lg w-full max-w mx-10'>
            <h2 className='text-left text-black text-[1.7rem] mb-5'>History</h2>
        </div>
    </main>
  )
}
