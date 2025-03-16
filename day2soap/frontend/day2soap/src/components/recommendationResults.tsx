import React, { useEffect } from 'react'

const RecommendationResults = ({recommendation}) => {
    return (
        <main className='mt-3 bg-white p-6 rounded-lg shadow-lg w-full'>
            <div className='fle'>
                <h2 className='text-left text-black text-[1.7rem] mb-5'>Results</h2>
            </div>

            <div>
                {recommendation.map((recommendation, index) => (
                    <div key={index} className='text-black mb-5'>
                        <h2 className="font-bold">{recommendation.title} - {recommendation.year}</h2> 
                        <p>{recommendation.description}</p>

                    </div>
                ))}
            </div>
        </main>
    )
};

export default RecommendationResults;