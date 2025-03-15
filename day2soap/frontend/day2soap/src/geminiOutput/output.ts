export async function getOutput() {
    try {
        const post_response = await fetch("/test.json");
        if (post_response.ok) {
            alert("Recommendation generated");
            const recommendationData = await post_response.json();

            const recommendations = recommendationData.map((element: any) => {
                return { 
                    title: element.title,
                    year: element.year,
                    description: element.description,
                }
            });

            console.log(recommendations);
            return recommendations;
        } else {
            alert("Recommendation failed");
            return [];
        }
    } catch (error) {
        console.log(error);
        return [];
    }
}

getOutput().then(fetchedRecommendation => {
    recommendations = fetchedRecommendation;
  });
  
export let recommendations: { title: string; year: number; description: string }[] = [];