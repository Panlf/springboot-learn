Cluster	集群，包含多个节点，每个节点属于哪个集群是
		通过配置（集群名称，默认elasticsearch）来决定，

Node	节点，集群中的一个节点，节点也有一个名称（默认是随机分配的），节点名称很重要
		（在执行运维管理的时候），默认节点会去加入一个名称为elasticsearch的集群，
		如果直接启动一堆节点，那么他们会自动组成一个elasticsearch集群，
		当然一个节点也可以组成一个elasticsearch集群

Document	文档，es中的最小数据单元，一个document可以是一条客户数据、商品分类数据等，
			通常使用JSON数据结构表示，每个index下的type中，都可以去存储多个document。
			一个document里面有多个field，每个field就是一个数据字段

Index 索引，包含一堆相似结构的文档数据，比如一个客户的索引、商品分类索引等，索引也有名称

Type	类型，每个索引都可以有一个或者多个type，type是index中的一个逻辑分类，
		一个type下的document，都有相同的Field，比如博客系统，有一个索引，可以定义用户数据type，
		博客数据type等

shard	单台机器无法存储大量数据，es可以将一个索引中的数据切分为多个shard，分布在多台服务器上存储。
		有了shard就可以横向扩展，存储更多的数据，让搜索和分析等操作分布到多台服务器上执行
		提高吞吐量和性能。每个shard都是一个lucene index

replica	任何一个服务器随时可能故障或宕机，此时shard可能就会丢失，因此可以为每个shard创建
	多个replica副本。replica可以在shard故障时提供备用服务，保证数据不丢失，多个replica
	还可以提升搜索操作的吞吐量和性能。primary shard（建立索引时一次设置，不能修改，默认5个），
	replica shard（随时修改数量，默认1个），默认每个索引10个shard，5个primary shard，5个replica shard
	最小的高可用配置，是2台服务器。

Index  数据库
type 	表
document 行