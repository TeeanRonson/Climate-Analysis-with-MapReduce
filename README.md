# Climate Analysis with Map Reduce on US Weather Data

**Project Goal:** Analyzing US Weather data to make inferences and inform direction on a series of questions.

**Deliverables:**

1. Code for MapReduce jobs
2. A project report

**Background: USCRN**

The vision of the USCRN program is to maintain a sustainable high-quality climate observation network that 50 years from now can with the highest degree of confidence answer the question:

> *How has the climate of the Nation changed over the past 50 years?*

**Dataset:**

The dataset is collected from the National Oceanic and Atmospheric Administration’s (NOAA) *surface reference network* (USCRN). The network is composed of around 150 weather stations based in the USA and is tasked with determining how the US climate has changed (and is changing) over time.

Some background on the data collected:

1. Three independent measurements of temperature and precipitation are made at each station, insuring continuity of record and maintenance of well-calibrated and highly accurate observations.
2. The stations are placed in pristine environments expected to be free of development for many decades. Stations are monitored and maintained to high standards and are calibrated on an annual basis.
3. In addition to temperature and precipitation, these stations also measure solar radiation, surface skin temperature, and surface winds.
4. They also include triplicate measurements of soil moisture and soil temperature at five depths, as well as atmospheric relative humidity for most of the 114 contiguous U.S. stations.

**Notes on the report:**

All of the questions are answered with **context**. Given a question, we embed maps and provide some statistics about it (population, nearby landmarks). Pictures and maps are a good thing. if the question involves some obscure concept; in that case, a link to the appropriate Wikipedia article is presented. Combining different forms of media through *data fusion* can tell a compelling story.

### Extremes: When and where was the hottest and coldest surface and air temperatures observed in the dataset? Are they anomalies? If so, what were the hottest and coldest non-anomalous temperatures?

We look at extreme weather patterns in the dataset for each region as determined by a 3 digit Geohash string. Specifically, for both surface and air temperatures, we:

- Calculate the maximum (hottest) and minimum (coldest) temperatures, measured in degrees Celsius.
- We then compare these maximums and minimums across all regions and across all years to find the Location (Where) and TimePeriod(When) these temperatures were experienced.

**Hottest Surface Temperature & Coldest Surface Temperature** 

The hottest surface temperatures were found 

highest surface temperature = 9qy

lowest surface temperature = 9r0

![](question1_graph2-0dbf2b17-4254-4a9f-b9c5-bf120b1d90e2.png)

**Hottest Air Temperatures & Coldest Air Temperatures** 

Our Map Reduce job found that the hottest and coldest air temperatures in the dataset were found in Geohash location: 9w9. Just within the Glen Canyon National Recreation Area. Though the data set does not provide the elevation of the exact location the temperatures were recorded, the latitude and longitude coordinates found showed that the area had a peak of 7,606 feet (2,319 m) elevation. 

![](question1_graph1-4fc2b37c-8163-40bb-922f-ae40449753a7.png)

![](GlenCanyon-7e24b208-7b4b-4e8c-9adb-260e79897026.jpg)

Glen Canyon Winter. Source: Google

![](GLSUmmer-0e6cc601-0d97-497c-9ca3-2c5bf5773318.jpg)

Glen Canyon Summer. Source: Google

### Drying out: Choose a region in North America (defined by Geohash, which may include several weather stations) and determine when its driest month is. This should include a histogram with data from each month.

Selected region: 9qd - California (See map below) 

![](Screenshot_2019-11-16_at_13-566caf38-897e-4dd4-9949-76dfa5be39fa.02.34.png)

To define dry weather, we consider the following data points: 

1. Precipitation [10] - Consider lower values since lower values indicate dryness. 
2. Relative Humidity[16] - Consider lowest values since lowest value indicate low relative humidity and greater dryness. 
3. Soil Moisture [18] - Consider the lowest values since lowest value indicate low soil moisture and greater dryness.
4. Soil Temperature [19] - Consider high values since higher values indicate dryness.
5. Wetness [20] - High values (>= 1000) indicate an absence of moisture.

**Results:**

- The NCDC data set has unusable data for both soil moisture and soil temperature, leaving us unable to use this in our evaluation.
- We compare the wetness and humidity of the region across all months throughout the period from 2011 to 2019.

**Time period Graph**

![](question2_graph1_(2)-4bbbb582-2ab3-466e-a926-16f31d644da8.png)

Time period from 2011 - 2019. Annual Humidity and Wetness for region 9qd. 

**Annual Graphs**

![](question2_graph2-c86edb92-a02b-4c06-82c4-bf13ac8f9d0b.png)

2011, 2012, 2013, 2014: Monthly Wetness and Humidity plots for region 9qd. 

![](question2_graph3-036212b8-5530-406b-97af-71d19a606497.png)

2015, 2016, 2017, 2018: Monthly Wetness and Humidity plots for region 9qd. 

![](question2_graph4_(1)-c9a2f9e9-8ed7-4506-b1c1-455cf97a5ad5.png)

2019: Monthly Wetness and Humidity plots for region 9qd.

- As seen from the graphs above, the driest months as determined by High Wetness and Low relative humidity values indicate the driest months are usually late in the year, around the September to November time period.
- This is in fact in line with California Fire Season, which occurs in the Fall. See links:
    - [https://www.theguardian.com/us-news/2019/nov/01/california-wildfire-season-2019](https://www.theguardian.com/us-news/2019/nov/01/california-wildfire-season-2019)
    - [https://en.wikipedia.org/wiki/2019_California_wildfires](https://en.wikipedia.org/wiki/2019_California_wildfires)

### **Moving out: Matthew, a student in your Big Data class, really likes the Bay Area weather but due to financial limitations will never be able to own a house there. Find similarly-sized regions with similar weather patterns so Matthew can move away for good.**

### **You should consider more than just one or two features from the dataset here, and think carefully about your methodology.**

**Weather Patterns in the Bay Area**

To find weather conditions in the Bay Area, we consider the following variables: 

- Air Temperature [9] - (Celsius)
- Precipitation [10] - (mm)
- Relative Humidity [16] - %
- Wind_1_5 [22] - m/s

The data set provides data on Geo hash location 9qb, which, though not exactly the Bay Area, sits just above the Bay Area and so will have similar weather patterns. We calculate the monthly average of each data point across all four variables in this region over the 2012-2019 period. 

![](question3_graph-c0b0bc90-c2e1-4aba-a2cc-e4d6560c4fef.png)

Bay Area Average Weather from 2012 - 2019: Humidity, Air Temperature, Precipitation, Wind

![](Screenshot_2019-11-16_at_13-b54a20aa-8947-4db9-b1f6-1229815b5a07.48.11_1.png)

**Equivalent Region Weather Patterns**

We run a Map Reduce job to find all locations that fall within a range underpinned by the Bay Area Monthly Average Weather above. For instance, if the average air temperature in the Bay Area for April 2018 is 12 degrees celsius, then an equivalent region has to have an average April 2018 air temperature of 10 - 14 degrees celsius. We set the ranges as follows: 

- Air Temperature [9] - (Celsius): +- 2 degrees celsius of Bay Area Air Temperature
- Precipitation [10] - (mm): +- 5mm of Bay Area Precipitation
- Relative Humidity [16] - (%): +- 5% of Bay Area Relative Humidity
- Wind_1_5 [22] - m/s: +- 0.8 of Bay Area Average Wind Speeds

Our results conclude with two locations that most frequently have monthly weather data within the the given ranges, and so most similar to Bay Area weather. 1. Geo hash region: c1f - Metlakatla, South Alaska. 2. Geo hash region: 9px - Medford, Oregon. 

![](question3_comparison_graph-786700c0-f8a5-4796-a175-271e17b8137f.png)

Geo hash: 9px Medford, Oregon comparison to Bay Area Weather.

![](Screenshot_2019-11-16_at_15-3c6a1113-4a9e-4dcd-a8b4-eb51d098102f.42.26.png)

![](question3_comparison_graph_(2)-8844ab90-a3e4-4894-aa95-5158b5144bb4.png)

Geo hash: c1f Metlakatla, Alaska comparison to Bay Area Weather. 

![](Screenshot_2019-11-16_at_15-be47ec9f-660c-4e35-9f82-5626c4b13548.45.28.png)

Based on these results, we recommend moving to Medford, Oregon as the annual weather pattern in Medford is less volatile than that of Metlakatla. The highest and lowest temperatures in Metlakatla are much more extreme than that of Medford, even though the average weather falls within the range that we have set. 

### ***Travel Startup*: After graduating from USF, you found a startup that aims to provide personalized travel itineraries using big data analysis. Given your own personal preferences, build a plan for a year of travel across 5 locations. Or, in other words: pick 5 regions. What is the best time of year to visit them based on the dataset?**

### **Part of your answer should include the *comfort index* for a region. There are several different ways of calculating this available online. Note: you don’t need to use this for choosing the regions, though.**

The comfort index is an arbitrary index of the suitability of environmental conditions to physical activity. Comfort Index = (temperature + relative humidity)/4.

The 5 travel regions that we have selected are: 

- Alaska
- California
- Orlando
- New Orleans
- Grand Canyon

Of these 5 locations, we calculate the following variables on a monthly basis to determine the best time of year to visit: 

- Comfort Index
- Air Temperature [9] - (Celsius)
- Precipitation [10] - (mm)
- Relative Humidity [16] - (%)
- Wind_1_5 [22] - (m/s)

**Alaska**

![](image_(4)-93f6bdb1-210c-494e-903d-416ddd5ce7d3.png)

Alaska Comfort Index and Air Temperature Averages over 2011-2019. 

![](image_(3)-6a9e2437-4dee-4ed4-98b9-a493d614eeba.png)

Alaska Precipitation and Humidity Averages over 2011 - 2019. 

Breaking down the data set: 

The best time of year to visit Alaska is during the Summer months and this can clearly be seen from the graphs above: 

- The comfort index is highest during the Summer months, and reaches its peaks above 30 in August, in every year throughout the period.
- The comfort index value in August is supported by the Air Temperature and Humidity graphs above. Which also shows the Summer months as the best time to visit Alaska.
- We do however see that the precipitation is highest in August despite the optimal Air Temperature and Humidity. This is expected since precipitation is correlated with Air Temperature and Humidity.

**Orlando**

![](image_(6)-824e11f1-cbca-4f90-a02a-e866f7f84418.png)

Orlando Comfort Index and Air Temperature Averages over 2011-2019. 

![](image_(5)-2936c3da-04b3-484c-944d-814c5d18c24b.png)

Orlando Precipitation and Humidity Averages over 2011 - 2019. 

Breaking down the data set: 

Orlando generally has reasonable weather throughout the year if we simply look at these graphs at face value. We can see that the Comfort Index is at stable range between 30-40. The air temperature is generally quite comfortable, bar the colder months at the start and at the end of the years. Nonetheless, there is reason to pay attention to the precipitation graphs here due to adverse weather conditions in the Florida region due to Atlantic Hurricanes: 

- In the Northern Atlantic Ocean, a distinct hurricane season occurs from June 1 to November 30, sharply peaking from late August through September
- This may be reflected in our precipitation graph as shown in the higher precipitation levels from June down to October.
- As such, travelling to Orlando or the Florida region during this season can be avoided.

[https://en.wikipedia.org/wiki/Atlantic_hurricane_season](https://en.wikipedia.org/wiki/Atlantic_hurricane_season)

**California**

![](image_(7)-85a8a890-3c80-4471-b992-ac5ead8455ae.png)

California Comfort Index and Air Temperature Averages over 2011-2019. 

![](image_(8)-bac59a43-509a-488c-a936-c72f5e6dcb65.png)

California Precipitation and Humidity Averages over 2011 - 2019. 

Breaking down the data set: 

California generally has good weather throughout the year as shown in the graphs above. In fact, California overall has one of the better and stable weathers across the United States. However, we should point out our findings in precipitation data: 

- California has very low levels of precipitation, as shown being in the range of 0.2 -0.6.
- This makes California very prone to Wildfires and the California Fire Season is best avoided.
- California Fire season usually begins in October and lasts till until April. [https://www.cheatsheet.com/culture/when-is-fire-season-in-california.html/](https://www.cheatsheet.com/culture/when-is-fire-season-in-california.html/)
- As such our recommendation is to avoid this period. However, it is worth noting that in recent years, fire season has gotten worse and California has experienced its worst fires in the last 2 years, with fire warnings happening year round.

**Grand Canyon** 

![](image_(9)-236cd2a4-c598-446b-8469-d74c6ae50d0b.png)

Grand Canyon Comfort Index and Air Temperature Averages over 2011-2019. 

![](image_(10)-e5675ec5-e2ce-40a2-a299-f927df9a5af8.png)

Grand Canyon Precipitation and Humidity Averages over 2011 - 2019. 

Breaking down the data set:

- The best time to visit the Grand Canyon based on the data is within the May June period.
- At this time the weather isn't too hot and ideal for travel since its within the cool temperature region.
- We want to note that although not included here, the solar radiation as it approaches the summer can be stronger, hence strengthening the fact to visit in the May June period.

**New Orleans**

![](question4_no1-8b019f86-5645-40cd-bcd3-160d0b0195ff.png)

New Orleans Comfort Index and Air Temperature Averages Over 2011-2019. 

![](question4_no2-dce36b0a-1aa9-42f9-ba56-0032c268a7f5.png)

New Orleans Precipitation and Humidity Averages over 2011 - 2019. 

Breaking down the data set: 

- The comfort index for New Orleans is stable throughout the year, and so a visit to New Orleans any time of the year is reasonable.
- We want to point out that in the hotter summer months, however, insects are plenty (mosquitoes, etc) and can be avoided by visiting the area just when the weather starts to get cooler in the Fall.
- Note however that the humidity levels pick back up during this period, though the changes are not huge, which may make individuals more prone to allergies in the Bayou area.

### Overall Travel Strategy:

- March - April:  Orlando
- April - May: California
- May - June: Grand Canyon
- July - August: Alaska
- August - September: New Orleans

### ***SolarWind, Inc.*: You get bored enjoying the amazing views from your mansion that you bought with the money made with your travel startup, so you start a new company; here, you want to help power companies plan out the locations of solar and wind farms across North America. Locate the top 3 places for solar and wind farms, as well as a combination of both (solar + wind farm). You will report a total of 9 Geohashes as well as their relevant attributes (for example, cloud cover and wind speeds).**

### **If you’d like to do some data fusion to answer this question, the maps [here](https://windexchange.energy.gov/maps-data/319) and [here](https://windexchange.energy.gov/maps-data/321) might be helpful.**

![](question5_2-0896fd18-5aa8-45d9-9759-09705d7c97e6.png)

![](question5_1-5b390188-ec93-461e-9caa-57e27d0a4cf8.png)

![](question5_3-bfc1c24f-a4bc-4c7d-be44-b0d08dcd362e.png)

**Solar Farm Top 3 Locations** 

1. SouthWest to Houston, Texas. 
    1. Geohash: 9v5
    2. Solar Radiation: 897.0
    3. Wind Speed: 2.65919
2. NorthEast to San Antonio, Texas. 
    1. Geohash: 9v6
    2. Solar Radiation: 898.0
    3. Wind Speed: 2.392514
3. West to New Orleans Louisiana.
    1. Geohash: 9vq
    2. Solar Radiation: 908.0
    3. Wind Speed 2.1608994

**Wind Farm Top 3 Locations**

1. Pocatello, Idaho 
    1. Geohash: 9rx
    2. Solar Radiation: 340.0
    3. Wind Speed: 6.0013638
2. KeyStone, Nebraska 
    1. GeoHash: 9xr
    2. Solar Radiation: 215.0
    3. Wind Speed: 6.5041623
3. Cheyenne, Wyoming
    1. Geohash: 9xm 
    2. Solar Radiation: 262 
    3. Wind Speed: 6.210097

**Solar + Wind Farm Top 3 Locations**

![](Screenshot_2019-11-17_at_15-9e7c333a-bf62-4dec-8b80-1984a203f01d.33.38.png)

NorthEast San Antonio, Texas

![](Screenshot_2019-11-17_at_15-5b29a046-7f43-463c-a70f-860f169c6a12.32.37.png)

KeyStone, Nebraska. 

![](Screenshot_2019-11-17_at_15-1b15d1e2-8472-43b5-aed3-93f1780dfa17.33.00.png)

Cheyenne, Wyoming. 

1. KeyStone, Nebraska 
    1. GeoHash: 9xr
    2. Solar Radiation Average: 872
    3. Wind Speed: 6.5041623
2. Cheyenne, Wyoming
    1. Geohash: 9xm 
    2. Solar Radiation: 262 
    3. Wind Speed: 6.210097
3. NorthEast to San Antonio, Texas. 
    1. Geohash: 9v6
    2. Solar Radiation: 898.0
    3. Wind Speed: 2.392514

**Selection Background**

There are several factors to consider when selecting the location to build solar and wind farms. To make our decision, we have considered those below and used the weather data obtained from NCDC to help our selection process: 

**Solar Farms & Wind Farms**

1. Quality of terrain
    1. Sloped land, excessively rocky or sandy terrain, uneven land etc can all significantly add to the cost of installing a solar farm.
2. Local weathering factors
    1. Desert conditions often coincide with excessive dust fall, flooding and flash flooding, high erosion etc, and these can limit the viability of a site and in many cases can make a site non-viable. 
    2. As sunlight passes through the atmosphere, some of it is absorbed, scattered, and reflected by:
        1. Air molecules
        2. Water vapor
        3. Clouds
        4. Dust
        5. Pollutants
        6. Forest fires
        7. Volcanoes.
    3. Wind speed is a crucial element in projecting turbine performance, and a site's wind speed is measured through wind resource assessment prior to a wind system's construction. Generally, annual average wind speeds greater than four meters per second (m/s) (9 mph) are required for small wind electric turbines (less wind is required for water-pumping operations). Utility-scale wind power plants require minimum average wind speeds of 6 m/s (13 mph).
3. Local Transmission Capacity to Power Grids
    1. In many places, power grids are not able to handle the excess capacity that a solar farm would introduce. This has been a persistent problem in California, Spain, Ontario and the Middle East, and has led to hundreds of projects being cancelled or delayed indefinitely.
4. Location 
    1. Countries such as the United States, which lie in the middle latitudes, receive more solar energy in the summer not only because days are longer, but also because the sun is nearly overhead. The sun's rays are far more slanted during the shorter days of the winter months. Cities such as Denver, Colorado, (near 40° latitude) receive nearly three times more solar energy in June than they do in December.
    2. The solar resource across the United States is ample for photovoltaic (PV) systems because they use both direct and scattered sunlight. Other technologies may be more limited. However, the amount of power generated by any solar technology at a particular site depends on how much of the sun's energy reaches it. Thus, solar technologies function most efficiently in the southwestern United States, which receives the greatest amount of solar energy.

References: 

[https://www.energy.gov/eere/solar/articles/solar-radiation-basics](https://www.energy.gov/eere/solar/articles/solar-radiation-basics)

**[https://morgansolar.wordpress.com/2009/04/14/solar-farm-site-selection/](https://morgansolar.wordpress.com/2009/04/14/solar-farm-site-selection/)**

[https://www.quora.com/What-is-the-minimum-amount-of-solar-radiation-required-for-setting-up-a-solar-farm](https://www.quora.com/What-is-the-minimum-amount-of-solar-radiation-required-for-setting-up-a-solar-farm)

[https://morgansolar.wordpress.com/2009/04/14/solar-farm-site-selection/](https://morgansolar.wordpress.com/2009/04/14/solar-farm-site-selection/) 

[https://www.energycentral.com/c/gn/initial-steps-building-solar-farm](https://www.energycentral.com/c/gn/initial-steps-building-solar-farm)

[https://www.energysage.com/solar/community-solar/key-considerations/](https://www.energysage.com/solar/community-solar/key-considerations/)

### *Climate Chart*: Given a Geohash prefix, create a climate chart for the region. This includes high, low, and average temperatures, as well as monthly average rainfall (**precipitation**). [Here’s a (poor quality) script](https://github.com/malensek/climate-chart) that will generate this for you.

### Earn up to 1 point of extra credit for enhancing/improving this chart (or porting it to a more feature-rich visualisation library)

![](question6_graph1-d8e512fc-526a-45d7-be99-6222ef53954e.png)

### *Correlation is not Causation*: Determine how features influence each other using [Pearson’s correlation coefficient (PCC)](https://en.wikipedia.org/wiki/Pearson_correlation_coefficient). The output for this job should include (1) feature pairs sorted by absolute correlation coefficient, and (2) a correlation matrix visualization (heatmaps are a good option).

![](question7_2-65be39b5-b555-4ede-abd0-6a5e5d2cf712.png)

![](image_(11)-8ddbab4a-cf90-420b-a377-d5af5a41d5ce.png)

In the Pearson correlation coefficient, we have values between +1 and -1 where 1 indicates positive linear correlation, 0 is no linear correlation, and −1 indicates negative linear correlation. According to the coefficient matrix, we see that air temperature and precipitation are negatively correlated. Also surface temperature and precipitation around in the same range as air temperature.

This is true because when there is precipitation/rain, it causes the air's dew points to increase, the humidity increases and thus air temperature and surface temperatures go down. So as the precipitation increases, the air temperature and surface temperature decreases. We also see that solar radiation and precipitation are very much negatively related. Precipitation causes solar radiation to decrease and thus are negatively related. Talking about some positively related features

like humidity and wetness, they are positive because the the wetness and humidity both increase the water content in the air. humidity causes wetness to increase and vice versa. Therefore we see that they are highly positive. The other feature wind, is negatively related with other features because their high presence impacts wind negatively. For example, precipitation and humidity causes the wind to decrease ( the direction and time of the wind flow also plays an important role in it's relation with other features ).

### **Now that you’re familiar with the dataset, it’s time to choose your own adventure. Come up with a question that you will likely be able to answer with climate data, and then implement a MapReduce job (or set of jobs) to answer the question. This question is worth the most points, so it should be more sophisticated than the others.**

Dear Matthew,

We’ve researched several strategies that will help with your financial situation, possibly opening up an opportunity to purchase a home in the Bay Area and live there instead of moving away. Specifically, we have a weather-first strategy in determining soybean crop yields in the coming years, in which you can use to trade Soybeans Futures on the Chicago Board of Trade (CBOT) and Tokyo Grain Exchange (TGE).

**Some background**

Weather in the US is a significant factor affecting Soybean yields, amongst other things (global trade, government subsidies, and consumer demand). We use weather data from NCDC to measure future soybean crop yields.

Soybeans futures are standardized, exchange-traded contracts in which the contract buyer agrees to take delivery, from the seller, a specific quantity of soybeans (eg. 5000 bushels) at a predetermined price on a future delivery date.

CBOT Soybeans futures prices are quoted in dollars and cents per bushel and are traded in lot sizes of 5000 bushels ( bu) (36 metric tons).

**The Market and Stakeholders**

- Consumers and producers of soybeans can manage soybeans price risk by purchasing and selling soybeans futures.
- Soybeans producers can employ a [short hedge](https://www.theoptionsguide.com/soybeans-futures-short-hedge.aspx) to lock in a selling price for the soybeans they produce while businesses that require soybeans can utilize a [long hedge](https://www.theoptionsguide.com/soybeans-futures-long-hedge.aspx) to secure a purchase price for the [commodity](https://www.theoptionsguide.com/commodity.aspx) they need.
- Soybeans futures are also traded by investors (Matthew) who assume the price risk that hedgers try to avoid in return for a chance to profit from favorable soybeans price movement.
- Investors (Matthew) [buy soybeans futures](https://www.theoptionsguide.com/soybeans-futures-buying.aspx) when they believe that soybeans prices will go up. Conversely, they will [sell soybeans futures](https://www.theoptionsguide.com/soybeans-futures-selling.aspx) when they think that soybeans prices will fall.

**Strategy - Shorting**

If you are bearish on soybeans, you can profit from a fall in soybeans price by taking up a [short position](https://www.theoptionsguide.com/short-futures.aspx) in the soybeans futures market. You can do so by selling (shorting) one or more [soybeans futures contracts](https://www.theoptionsguide.com/soybeans-futures.aspx) at a futures exchange.

Example: Short Soybeans Futures Trade

1. You decide to go short one near-month CBOT Soybeans Futures contract at the price of USD 9.6900/bushel.
2. Since each Soybeans futures contract represents 5000 bushels of soybeans, the value of the contract is USD 48,450.
3. To enter the short futures position, you have to put up an [initial margin](https://www.theoptionsguide.com/futures-margin.aspx#initial) of USD 4,725. ~ 10% upfront.
4. A week later, the price of soybeans falls and correspondingly, the price of CBOT Soybeans futures drops to USD 8.7210 per bushel.
5. Each contract is now worth only USD 43,605. So by closing out your futures position now, you can exit your short position in Soybeans Futures with a profit of USD 4,845.
- Short Soybeans Futures Strategy: Sell HIGH, Buy LOW.
- SELL 5000 bushels of soybeans at USD 9.6900/bu. USD 48,450
- BUY 5000 bushels of soybeans at USD 8.7210/bu USD 43,605
- Profit USD 4,845
- Investment (Initial Margin) USD 4,725 Return on Investment 102.5397%

Disclaimer: The above example depicts a positive scenario whereby the market is favorable towards you. If the market turns against you, it will be at **your own risk**.

**Determining Soybean Yields with the Weather**

Soybean markets follow a pattern of a fixed cycle production which starts from planting to podding and harvesting. Each of these three stages in the soybean crop development has an influence on the final futures prices as it impacts the supply side of the chain.

- August weather is critical.
- Studying August weather conditions may help us understand high soybean yields.

Three weather factors greatly affect yield:

1. Rain - Precipitation; Soil Moisture; Soil Temperature; Wetness
    1. Know that for many parts of Missouri rain essentially stops after mid-August. However, soils that have good to excellent water holding capacity entered late summer with fully charged soil profiles.
    2. Stored water helped maintain yields of May planted soybeans in regions where soils are deep or textures are conducive to storing plant available water.
2. Sunlight - Solar Radiation
    1. Sunlight is important because it drives photosynthesis and photosynthesis produces the energy used to make yield.
    2. Soybean leaves are displayed to capture sunlight. Leaves need to capture that sun energy, store the captured energy in sugar, and translocate that sugar to seeds and other active parts of the plant.
    3. Increased light capture means greater sugar production. More sugar means more pods retained, and potentially greater yield.
3. Temperature - Air Temperature, Surface Temperature
    1. Temperature is important for two reasons. Water evaporation from leaves is directly related to the temperature of leaves.
    2. Bright sun and warm air temperatures increase water evaporation.
    3. Optimum temperature for soybean photosynthesis is 29°C.
    4. Averaged over 5 years, it is normal for 11 August days to experience temperatures greater than 29°C.
    5. The last two weeks of August are important because this is when rapid seed-filling occurs. Sugar that is produced in leaves must be transported to pods where it is transformed into oil, protein and complex carbohydrates.
    6. These compounds are stored in seeds to give us yield. If sugar is respired away because of warm temperatures it cannot be used to make yield.

**Adverse affects to Soybean Yield.**

- Soybeans are a temperate leguminous plant with an ideal daytime temperature of 29° C.
- When air temperatures exceed 29° C, soybeans can experience heat stress regardless of reproductive stage.
- When soybeans experience heat stress, yield reductions can begin to occur, especially when soil moisture is limiting.
- Heat stress during flowering can result in pollen sterility and reduced seed set. Temperatures exceeding 29° C can result in a decreased number of pods while temperatures above 37°C severely limit pod formation.
- During seed fill, daytime temperatures of 32 - 35° C result in fewer seeds per plant. Daytime temperatures greater than 29° C during seed fill can result in decreased soybean weight.

**The objective**

We use weather data to predict if the supply of Soybeans will increase or decrease, which will determine the strategy to either be long or short on Soybeans Futures.

![](pie-799c43e2-a296-4ee4-8e8a-f29a65cec519.png)

U.S. Soybean Area Planted by State

![](pie_(1)-c7c56129-2cd1-44d8-898f-16553892d4a8.png)

U.S. Soybean Production by State. /per million bushels

![](Screenshot_2019-11-17_at_13-4a9ec68f-7565-4622-a3f2-b22edb143906.10.54.png)

U.S. Soybean Production 1988 - 2017. Source: ndsoygrowers.com

We consider 9 states that make up ~70% or more of the total area planted, and ~70% of the overall U.S. production. In numbers, these states take up 64,200 Acres of the 90,142 Acres of area planted, and produce 3,222 of 4,391 million bushels produced: 

- Iowa
- Illinois
- Minessota
- N. Dakota
- Nebraska
- Missouri
- Indiana
- Kansas
- Ohio

**Results**

1. Rain -  Precipitation; Soil Moisture; Soil Temperature; Wetness
    1. We omit graphs for Precipitation, Wetness, Soil Moisture and Soil Temperature here because the areas selected usually experience little Precipitation and Wetness and values for Soil Moisture and Soil Temperature are not available. 
    2. Nonetheless, we can assume that farmers provide plenty of hydration and water towards the crops and so, this factor is dealt with and at optimum levels. 
2. Sunlight - Solar Radiation

    ![](image_(12)-f00da769-ee26-43ba-87a4-57c8bebe2bd8.png)

    Solar Radiation Averages

    - We observe here that the Solar Radiation is generally between the range of 250 -500. The level of Solar Radiation isn't too strong for the Soybean crop, whilst still allows it maximum benefit in the photosynthesis process which produces the energy used to make yield.
3. Temperature - Air Temperature, Surface Temperature
    1. We highlight in orange the optimum average air temperature for Soybean yield. 
    2. In the graphs below, we observe that the August weather amongst the top Soybean producing states will have a steady fluctuation within this optimal temperature, hence perfect for Soybean yield. 

Our results suggest a possible over supply in soybean production, boosted by the favourable weather patterns in the US to soybean farmers, as well as the drop in demand for Soybean due to external factors such as the US-China Trade War. China is heavily seeking other importers in light of the increasing trade tensions between the US and China. 

Bottom Line: Short Soybean Futures fro 2020. 

![](question8_illinois-833f7e76-a67a-4e4f-ac50-c543e699c761.png)

Illinois August Temperature

![](question8_temp_indiana-88519a61-f6b8-4398-bc48-b103a38cd000.png)

Indiana August Temperature

![](question8_temp_iowa-81b16141-3c39-40ff-adb1-4cc43421d386.png)

Iowa August Temperature

![](question8_temp_indiana_(1)-741a1ec5-3f54-4e71-a8d6-40042ec93eca.png)

Kansas August Temperature

![](question8_temp_miness-65249b97-e962-40be-ac3d-419ab8ee1b7e.png)

Minessota August Temperature

![](question8_temp_nd-007fecc0-e38c-4c5a-aa0e-4f92292d8d7c.png)

N. Dakota August Temperature

![](question8_temp_missouri-f3a9ecee-b915-49a0-a813-b269210c9757.png)

Missouri August Temperature

![](question8_temp_oh-35c3a589-f046-47a1-81b2-7f4b990525fb.png)

Ohio August Temperature

References:

[https://ipm.missouri.edu/IPCM/2015/11/Weather-and-Soybean-Yields/](https://ipm.missouri.edu/IPCM/2015/11/Weather-and-Soybean-Yields/)

[https://www.theoptionsguide.com/soybeans-futures-buying.aspx](https://www.theoptionsguide.com/soybeans-futures-buying.aspx)

[https://www.theoptionsguide.com/soybeans-futures-selling.aspx](https://www.theoptionsguide.com/soybeans-futures-selling.aspx)

[https://tradingsim.com/blog/6-things-you-must-know-about-soybean-futures-trading/](https://tradingsim.com/blog/6-things-you-must-know-about-soybean-futures-trading/)

[https://www.farmprogress.com/corn/high-temperature-effects-corn-soybeans](https://www.farmprogress.com/corn/high-temperature-effects-corn-soybeans)

[https://ndsoygrowers.com/wp-content/uploads/2018/09/2018ASA-SoyStats.pdf](https://ndsoygrowers.com/wp-content/uploads/2018/09/2018ASA-SoyStats.pdf)
