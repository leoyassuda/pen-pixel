---
title: Pen-Pixel Entity Relationship Diagram 
---
erDiagram
Author {
    uuid id PK
    string name
    string email
}
Post {
    uuid id PK
    string text
    string authorId
    date createdAt
}

Author ||--o{ Post : post
