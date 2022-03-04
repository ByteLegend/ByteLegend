/*
 * Copyright 2021 ByteLegend Technologies and the original author or authors.
 *
 * Licensed under the GNU Affero General Public License v3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://github.com/ByteLegend/ByteLegend/blob/master/LICENSE
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bytelegend.app.jsonmodel.review;

import java.util.List;

/*
{
  "data": {
    "repository": {
      "pullRequests": {
        "edges": [
                    {
            "cursor": "Y3Vyc29yOnYyOpHOES1yDw==",
            "node": {
              "number": 6,
              "reviewThreads": {
                "nodes": [
                  {
                    "comments": {
                      "nodes": [
                        {
                          "body": "这里直接`return true`不好么？",
                          "author": {
                            "login": "blindpirate"
                          },
                          "commit": {
                            "oid": "c16cac5b5ef0c3697bc4e05bbea499bbba3f2391"
                          },
                          "pullRequest": {
                            "files": {
                              "nodes": [
                                {
                                  "path": "src/main/java/com/github/hcsp/controlflow/Main.java"
                                }
                              ]
                            }
                          }
                        },
                        {
                          "body": "不知所措.jpg",
                          "author": {
                            "login": "Li-saltair"
                          },
                          "commit": {
                            "oid": "c16cac5b5ef0c3697bc4e05bbea499bbba3f2391"
                          },
                          "pullRequest": {
                            "files": {
                              "nodes": [
                                {
                                  "path": "src/main/java/com/github/hcsp/controlflow/Main.java"
                                }
                              ]
                            }
                          }
                        },
                        {
                          "body": "@Li-saltair 这是习题课直播讲解的第一题，请看习题课的讲解视频：https://xiedaimala.com/tasks/a33b014c-9d14-455a-acd9-56f6251083a1",
                          "author": {
                            "login": "blindpirate"
                          },
                          "commit": {
                            "oid": "c16cac5b5ef0c3697bc4e05bbea499bbba3f2391"
                          },
                          "pullRequest": {
                            "files": {
                              "nodes": [
                                {
                                  "path": "src/main/java/com/github/hcsp/controlflow/Main.java"
                                }
                              ]
                            }
                          }
                        }
                      ]
                    },
                    "line": 24,
                    "originalLine": 24,
                    "originalStartLine": null,
                    "startLine": null,
                    "startDiffSide": null
                  }
                ]
              },
              "headRepository": {
                "nameWithOwner": "Li-saltair/symmetric-string"
              }
            }
          }
        ]
      }
    }
  }
}
 */
public class PullRequestReviewThreadResponse {

    /**
     * data : {"repository":{"pullRequests":{"edges":[{"cursor":"Y3Vyc29yOnYyOpHOES1yDw==","node":{"number":6,"reviewThreads":{"nodes":[{"comments":{"nodes":[{"body":"这里直接`return true`不好么？","author":{"login":"blindpirate"},"commit":{"oid":"c16cac5b5ef0c3697bc4e05bbea499bbba3f2391"},"pullRequest":{"files":{"nodes":[{"path":"src/main/java/com/github/hcsp/controlflow/Main.java"}]}}},{"body":"不知所措.jpg","author":{"login":"Li-saltair"},"commit":{"oid":"c16cac5b5ef0c3697bc4e05bbea499bbba3f2391"},"pullRequest":{"files":{"nodes":[{"path":"src/main/java/com/github/hcsp/controlflow/Main.java"}]}}},{"body":"@Li-saltair 这是习题课直播讲解的第一题，请看习题课的讲解视频：https://xiedaimala.com/tasks/a33b014c-9d14-455a-acd9-56f6251083a1","author":{"login":"blindpirate"},"commit":{"oid":"c16cac5b5ef0c3697bc4e05bbea499bbba3f2391"},"pullRequest":{"files":{"nodes":[{"path":"src/main/java/com/github/hcsp/controlflow/Main.java"}]}}}]},"line":24,"originalLine":24,"originalStartLine":null,"startLine":null,"startDiffSide":null}]},"headRepository":{"nameWithOwner":"Li-saltair/symmetric-string"}}}]}}}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * repository : {"pullRequests":{"edges":[{"cursor":"Y3Vyc29yOnYyOpHOES1yDw==","node":{"number":6,"reviewThreads":{"nodes":[{"comments":{"nodes":[{"body":"这里直接`return true`不好么？","author":{"login":"blindpirate"},"commit":{"oid":"c16cac5b5ef0c3697bc4e05bbea499bbba3f2391"},"pullRequest":{"files":{"nodes":[{"path":"src/main/java/com/github/hcsp/controlflow/Main.java"}]}}},{"body":"不知所措.jpg","author":{"login":"Li-saltair"},"commit":{"oid":"c16cac5b5ef0c3697bc4e05bbea499bbba3f2391"},"pullRequest":{"files":{"nodes":[{"path":"src/main/java/com/github/hcsp/controlflow/Main.java"}]}}},{"body":"@Li-saltair 这是习题课直播讲解的第一题，请看习题课的讲解视频：https://xiedaimala.com/tasks/a33b014c-9d14-455a-acd9-56f6251083a1","author":{"login":"blindpirate"},"commit":{"oid":"c16cac5b5ef0c3697bc4e05bbea499bbba3f2391"},"pullRequest":{"files":{"nodes":[{"path":"src/main/java/com/github/hcsp/controlflow/Main.java"}]}}}]},"line":24,"originalLine":24,"originalStartLine":null,"startLine":null,"startDiffSide":null}]},"headRepository":{"nameWithOwner":"Li-saltair/symmetric-string"}}}]}}
         */

        private RepositoryBean repository;

        public RepositoryBean getRepository() {
            return repository;
        }

        public void setRepository(RepositoryBean repository) {
            this.repository = repository;
        }

        public static class RepositoryBean {
            /**
             * pullRequests : {"edges":[{"cursor":"Y3Vyc29yOnYyOpHOES1yDw==","node":{"number":6,"reviewThreads":{"nodes":[{"comments":{"nodes":[{"body":"这里直接`return true`不好么？","author":{"login":"blindpirate"},"commit":{"oid":"c16cac5b5ef0c3697bc4e05bbea499bbba3f2391"},"pullRequest":{"files":{"nodes":[{"path":"src/main/java/com/github/hcsp/controlflow/Main.java"}]}}},{"body":"不知所措.jpg","author":{"login":"Li-saltair"},"commit":{"oid":"c16cac5b5ef0c3697bc4e05bbea499bbba3f2391"},"pullRequest":{"files":{"nodes":[{"path":"src/main/java/com/github/hcsp/controlflow/Main.java"}]}}},{"body":"@Li-saltair 这是习题课直播讲解的第一题，请看习题课的讲解视频：https://xiedaimala.com/tasks/a33b014c-9d14-455a-acd9-56f6251083a1","author":{"login":"blindpirate"},"commit":{"oid":"c16cac5b5ef0c3697bc4e05bbea499bbba3f2391"},"pullRequest":{"files":{"nodes":[{"path":"src/main/java/com/github/hcsp/controlflow/Main.java"}]}}}]},"line":24,"originalLine":24,"originalStartLine":null,"startLine":null,"startDiffSide":null}]},"headRepository":{"nameWithOwner":"Li-saltair/symmetric-string"}}}]}
             */

            private PullRequestsBean pullRequests;

            public PullRequestsBean getPullRequests() {
                return pullRequests;
            }

            public void setPullRequests(PullRequestsBean pullRequests) {
                this.pullRequests = pullRequests;
            }

            public static class PullRequestsBean {
                private List<EdgesBean> edges;

                public List<EdgesBean> getEdges() {
                    return edges;
                }

                public void setEdges(List<EdgesBean> edges) {
                    this.edges = edges;
                }

                public static class EdgesBean {
                    /**
                     * cursor : Y3Vyc29yOnYyOpHOES1yDw==
                     * node : {"number":6,"reviewThreads":{"nodes":[{"comments":{"nodes":[{"body":"这里直接`return true`不好么？","author":{"login":"blindpirate"},"commit":{"oid":"c16cac5b5ef0c3697bc4e05bbea499bbba3f2391"},"pullRequest":{"files":{"nodes":[{"path":"src/main/java/com/github/hcsp/controlflow/Main.java"}]}}},{"body":"不知所措.jpg","author":{"login":"Li-saltair"},"commit":{"oid":"c16cac5b5ef0c3697bc4e05bbea499bbba3f2391"},"pullRequest":{"files":{"nodes":[{"path":"src/main/java/com/github/hcsp/controlflow/Main.java"}]}}},{"body":"@Li-saltair 这是习题课直播讲解的第一题，请看习题课的讲解视频：https://xiedaimala.com/tasks/a33b014c-9d14-455a-acd9-56f6251083a1","author":{"login":"blindpirate"},"commit":{"oid":"c16cac5b5ef0c3697bc4e05bbea499bbba3f2391"},"pullRequest":{"files":{"nodes":[{"path":"src/main/java/com/github/hcsp/controlflow/Main.java"}]}}}]},"line":24,"originalLine":24,"originalStartLine":null,"startLine":null,"startDiffSide":null}]},"headRepository":{"nameWithOwner":"Li-saltair/symmetric-string"}}
                     */

                    private String cursor;
                    private NodeBean node;

                    public String getCursor() {
                        return cursor;
                    }

                    public void setCursor(String cursor) {
                        this.cursor = cursor;
                    }

                    public NodeBean getNode() {
                        return node;
                    }

                    public void setNode(NodeBean node) {
                        this.node = node;
                    }

                    public static class NodeBean {
                        /**
                         * number : 6
                         * reviewThreads : {"nodes":[{"comments":{"nodes":[{"body":"这里直接`return true`不好么？","author":{"login":"blindpirate"},"commit":{"oid":"c16cac5b5ef0c3697bc4e05bbea499bbba3f2391"},"pullRequest":{"files":{"nodes":[{"path":"src/main/java/com/github/hcsp/controlflow/Main.java"}]}}},{"body":"不知所措.jpg","author":{"login":"Li-saltair"},"commit":{"oid":"c16cac5b5ef0c3697bc4e05bbea499bbba3f2391"},"pullRequest":{"files":{"nodes":[{"path":"src/main/java/com/github/hcsp/controlflow/Main.java"}]}}},{"body":"@Li-saltair 这是习题课直播讲解的第一题，请看习题课的讲解视频：https://xiedaimala.com/tasks/a33b014c-9d14-455a-acd9-56f6251083a1","author":{"login":"blindpirate"},"commit":{"oid":"c16cac5b5ef0c3697bc4e05bbea499bbba3f2391"},"pullRequest":{"files":{"nodes":[{"path":"src/main/java/com/github/hcsp/controlflow/Main.java"}]}}}]},"line":24,"originalLine":24,"originalStartLine":null,"startLine":null,"startDiffSide":null}]}
                         * headRepository : {"nameWithOwner":"Li-saltair/symmetric-string"}
                         */

                        private int number;
                        private ReviewThreadsBean reviewThreads;
                        private HeadRepositoryBean headRepository;

                        public int getNumber() {
                            return number;
                        }

                        public void setNumber(int number) {
                            this.number = number;
                        }

                        public ReviewThreadsBean getReviewThreads() {
                            return reviewThreads;
                        }

                        public void setReviewThreads(ReviewThreadsBean reviewThreads) {
                            this.reviewThreads = reviewThreads;
                        }

                        public HeadRepositoryBean getHeadRepository() {
                            return headRepository;
                        }

                        public void setHeadRepository(HeadRepositoryBean headRepository) {
                            this.headRepository = headRepository;
                        }

                        public static class ReviewThreadsBean {
                            private List<NodesBeanXX> nodes;

                            public List<NodesBeanXX> getNodes() {
                                return nodes;
                            }

                            public void setNodes(List<NodesBeanXX> nodes) {
                                this.nodes = nodes;
                            }

                            public static class NodesBeanXX {
                                /**
                                 * comments : {"nodes":[{"body":"这里直接`return true`不好么？","author":{"login":"blindpirate"},"commit":{"oid":"c16cac5b5ef0c3697bc4e05bbea499bbba3f2391"},"pullRequest":{"files":{"nodes":[{"path":"src/main/java/com/github/hcsp/controlflow/Main.java"}]}}},{"body":"不知所措.jpg","author":{"login":"Li-saltair"},"commit":{"oid":"c16cac5b5ef0c3697bc4e05bbea499bbba3f2391"},"pullRequest":{"files":{"nodes":[{"path":"src/main/java/com/github/hcsp/controlflow/Main.java"}]}}},{"body":"@Li-saltair 这是习题课直播讲解的第一题，请看习题课的讲解视频：https://xiedaimala.com/tasks/a33b014c-9d14-455a-acd9-56f6251083a1","author":{"login":"blindpirate"},"commit":{"oid":"c16cac5b5ef0c3697bc4e05bbea499bbba3f2391"},"pullRequest":{"files":{"nodes":[{"path":"src/main/java/com/github/hcsp/controlflow/Main.java"}]}}}]}
                                 * line : 24
                                 * originalLine : 24
                                 * originalStartLine : null
                                 * startLine : null
                                 * startDiffSide : null
                                 */

                                private CommentsBean comments;
                                private int line;
                                private int originalLine;
                                private Object originalStartLine;
                                private Object startLine;
                                private Object startDiffSide;

                                public CommentsBean getComments() {
                                    return comments;
                                }

                                public void setComments(CommentsBean comments) {
                                    this.comments = comments;
                                }

                                public int getLine() {
                                    return line;
                                }

                                public void setLine(int line) {
                                    this.line = line;
                                }

                                public int getOriginalLine() {
                                    return originalLine;
                                }

                                public void setOriginalLine(int originalLine) {
                                    this.originalLine = originalLine;
                                }

                                public Object getOriginalStartLine() {
                                    return originalStartLine;
                                }

                                public void setOriginalStartLine(Object originalStartLine) {
                                    this.originalStartLine = originalStartLine;
                                }

                                public Object getStartLine() {
                                    return startLine;
                                }

                                public void setStartLine(Object startLine) {
                                    this.startLine = startLine;
                                }

                                public Object getStartDiffSide() {
                                    return startDiffSide;
                                }

                                public void setStartDiffSide(Object startDiffSide) {
                                    this.startDiffSide = startDiffSide;
                                }

                                public static class CommentsBean {
                                    private List<NodesBeanX> nodes;

                                    public List<NodesBeanX> getNodes() {
                                        return nodes;
                                    }

                                    public void setNodes(List<NodesBeanX> nodes) {
                                        this.nodes = nodes;
                                    }

                                    public static class NodesBeanX {
                                        /**
                                         * body : 这里直接`return true`不好么？
                                         * author : {"login":"blindpirate"}
                                         * commit : {"oid":"c16cac5b5ef0c3697bc4e05bbea499bbba3f2391"}
                                         * pullRequest : {"files":{"nodes":[{"path":"src/main/java/com/github/hcsp/controlflow/Main.java"}]}}
                                         */

                                        private String body;
                                        private AuthorBean author;
                                        private CommitBean commit;
                                        private PullRequestBean pullRequest;

                                        public String getBody() {
                                            return body;
                                        }

                                        public void setBody(String body) {
                                            this.body = body;
                                        }

                                        public AuthorBean getAuthor() {
                                            return author;
                                        }

                                        public void setAuthor(AuthorBean author) {
                                            this.author = author;
                                        }

                                        public CommitBean getCommit() {
                                            return commit;
                                        }

                                        public void setCommit(CommitBean commit) {
                                            this.commit = commit;
                                        }

                                        public PullRequestBean getPullRequest() {
                                            return pullRequest;
                                        }

                                        public void setPullRequest(PullRequestBean pullRequest) {
                                            this.pullRequest = pullRequest;
                                        }

                                        public static class AuthorBean {
                                            /**
                                             * login : blindpirate
                                             */

                                            private String login;

                                            public String getLogin() {
                                                return login;
                                            }

                                            public void setLogin(String login) {
                                                this.login = login;
                                            }
                                        }

                                        public static class CommitBean {
                                            /**
                                             * oid : c16cac5b5ef0c3697bc4e05bbea499bbba3f2391
                                             */

                                            private String oid;

                                            public String getOid() {
                                                return oid;
                                            }

                                            public void setOid(String oid) {
                                                this.oid = oid;
                                            }
                                        }

                                        public static class PullRequestBean {
                                            /**
                                             * files : {"nodes":[{"path":"src/main/java/com/github/hcsp/controlflow/Main.java"}]}
                                             */

                                            private FilesBean files;

                                            public FilesBean getFiles() {
                                                return files;
                                            }

                                            public void setFiles(FilesBean files) {
                                                this.files = files;
                                            }

                                            public static class FilesBean {
                                                private List<NodesBean> nodes;

                                                public List<NodesBean> getNodes() {
                                                    return nodes;
                                                }

                                                public void setNodes(List<NodesBean> nodes) {
                                                    this.nodes = nodes;
                                                }

                                                public static class NodesBean {
                                                    /**
                                                     * path : src/main/java/com/github/hcsp/controlflow/Main.java
                                                     */

                                                    private String path;

                                                    public String getPath() {
                                                        return path;
                                                    }

                                                    public void setPath(String path) {
                                                        this.path = path;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        public static class HeadRepositoryBean {
                            /**
                             * nameWithOwner : Li-saltair/symmetric-string
                             */

                            private String nameWithOwner;

                            public String getNameWithOwner() {
                                return nameWithOwner;
                            }

                            public void setNameWithOwner(String nameWithOwner) {
                                this.nameWithOwner = nameWithOwner;
                            }
                        }
                    }
                }
            }
        }
    }
}
