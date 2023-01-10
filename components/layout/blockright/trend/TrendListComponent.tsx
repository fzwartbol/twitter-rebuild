import TrendItem from "./TrendItem";

export default function TrendListComponent() {
  const results: {location: string, title: string, count: number}[] = [];
  
  return (
        <div className={"text-white flex-row w-full h-[348px] bg-[#1d1f23] rounded-lg mt-4 pt-4 pb-4 "}>
            <div className={"font-bold text-lg ml-4 pb-4"}>Trends for you</div>
            {results && 
            results.map(item => <TrendItem location={item.location} title={item.title} count={item.count} />) }
      </div>
  )
}