import React, { useEffect } from 'react'
import { getOutput } from '@/geminiOutput/output'
import { recommendations } from '@/geminiOutput/output'

export default function RecommendationResults() {
    useEffect(() =>{
        getOutput();
    }, []); 
    return (
        <main className='mt-3 bg-white p-6 rounded-lg shadow-lg w-full'>
            <div className='fle'>
                <h2 className='text-left text-black text-[1.7rem] mb-5'>Results</h2>
            </div>

            <div>
                {recommendations.map((recommendation, index) => (
                    <div key={index} className='text-black mb-5'>
                        <h2>{recommendation.title} - {recommendation.year}</h2> 
                        <p>{recommendation.description}</p>

                    </div>
                ))}
            </div>
        </main>
    )
}
