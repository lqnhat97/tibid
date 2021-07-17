CREATE TABLE `bid_order` (
  `id` int PRIMARY KEY,
  `user_id` int UNIQUE NOT NULL,
  `product_id` int NOT NULL,
  `start_price` float8,
  `price_step` float8,
  `status` varchar(255),
  `bid_start_time` long,
  `bid_end_time` long,
  `type` varchar(255),
  `bid_quantity` int,
  `created_at` varchar(255) COMMENT 'When order created',
  `modified_at` varchar(255) COMMENT 'When order modified'
);

CREATE TABLE `bid_ticket` (
  `id` int PRIMARY KEY,
  `bid_order_id` int UNIQUE NOT NULL,
  `price` float8,
  `status` varchar(255) COMMENT 'BIDDING, SUCCESSFUL',
  `created_at` varchar(255) COMMENT 'When order created',
  `modified_at` varchar(255) COMMENT 'When order modified'
);
