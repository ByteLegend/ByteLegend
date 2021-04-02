## i18n 

The directory structure of `i18n` is as follows:

```
- i18n
   |__ AA.yml
   |__ BB.yml
   |__ JavaIsland
       |__ AA.yml
```

All files in `i18n` ending with `.yml` will be collected to `common.en.json`/`common.zh_hans.json`/..., which usually contains general purpose
i18n texts, e.g. for website UI.

Directories in `i18n` will be searched recursively, in which the `.yml` will be collected as `JavaIsland.en.json`/`JavaIsland.zh_hans.json`/...