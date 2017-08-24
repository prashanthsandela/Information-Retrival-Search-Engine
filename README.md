# Information-Retrival-Search-Engine

Information Retrial is the process of required information from a set of data. There is no limit in size of data. I developed a Search Engine under the guidance of Mr. Akash Nanavati. 

### Numeric Info:

1) Wikipedia dataset download link(http://download.wikimedia.org/enwiki/latest/enwiki-latest-pages-articles.xml.bz2).
2) Dataset size when Compressed: ~10GB
3) Dataset size un-compressed: 45GB
4) No. of Nodes: 14,78,245
5) Considered Nodes: 11,865,654
6) No. of Tokens: 1,118,656,547
7) Size of Tokens(before Compression): 8.7GB
8) Size of Tokens(before Compression) with page id: 11.3GB
9) Size of Tokens(after Compression): 2.3GB
10) Size of Tokens(after Compression) with page id: 3.2GB

### Procedure:

1) Dataset is pure xml, which has page information in 'page' element. A lot of information regarding the page is available within 'page' element. Out of all the elements, we need to extract data from 'id' and 'text' elements.
2) A parser code for extracting id and tokens from the document and storing all the id info with tag was designed.
3) Generate a posting list, frequency of word in document.
4) Sort the posting list with external sort(created threads to speedup the process).
5) Compress the sorted posting list(dictionary compression and id compression).
6) Generate in-links for the all the pages.
7) Depending on the pagerank generate page rank.

--Full info in below blog--
http://www.prashanthsandela.com/
