export default function TrendItem({location, title, count}: {location: string, title: string, count: number}) {
  return (
        <div className={"flex-row hover:bg-[#26282d] hover:cursor-pointer pl-4"}>
            <div className={"text-sm text-gray-500"}>Trending in {location}</div>
            <div className={"font-bold text-base"}>{title}</div>
            <div className={"text-sm text-gray-500"}>{count} Tweets</div>
      </div>
  )
}